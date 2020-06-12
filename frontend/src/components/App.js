import React, { Component } from "react";
import "./App.css";
import PropTypes from "prop-types";
import { withStyles } from "@material-ui/core/styles";
import PrimarySearchAppBar from "./appbar/PrimarySearchAppBar";
import CardTemplate from "./cards/CardTemplate";
import Grid from "@material-ui/core/Grid";

const styles = theme => {
  return {
    alignCards: {
      [theme.breakpoints.up("xs")]: {
        justifyContent: "center",
        margin: 0
      },
      [theme.breakpoints.up("sm")]: {
        justifyContent: "flex-start",
        margin: 10
      }
    }
  };
};

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      part: "",
      solved: {
      }
    };
    this.setPuzzleResult = this.setPuzzleResult.bind(this);
    this.addPuzzleCards = this.addPuzzleCards.bind(this);
  }

  addPuzzleCards() {
    const cards = [];
    for (let i = Object.keys(this.state.solved).length; i >= 1; i--) {
      cards.push(
        <CardTemplate
          key={i}
          callback={this.setPuzzleResult}
          cardType={"puzzleCard"}
          day={this.state.solved[i].day}
          result={this.state.solved[i].result}
        />
      );
    }
    return cards;
  }

  render() {
    const { classes } = this.props;

    return (
      <div className="App">
        <PrimarySearchAppBar />
        <Grid
          className={classes.alignCards}
          container
          direction="row"
          alignItems="flex-start"
        >
          <CardTemplate callback={this.setPuzzleResult} cardType={"newCard"} />
          {this.addPuzzleCards()}
        </Grid>
      </div>
    );
  }

  setPuzzleResult(value) {
    let nextSolved = Object.keys(this.state.solved).length + 1;
    let solved = Object.assign({}, this.state.solved);
    solved[nextSolved] = value;
    this.setState({ solved });
  }
}

App.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(App);

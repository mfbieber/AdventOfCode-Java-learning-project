import React, { Component } from "react";
import PropTypes from "prop-types";
import { withStyles } from "@material-ui/core/styles";
import Card from "@material-ui/core/Card";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import IconButton from "@material-ui/core/IconButton";
import FloatingActionButtons from "../buttons/FloatingActionButtons";
import PuzzleCard from "./PuzzleCard";
import AddNewCard from "./AddNewCard";
import CircularProgress from "@material-ui/core/CircularProgress";

const styles = {
  card: {
    minWidth: 300,
    maxWidth: 300,
    minHeight: 300,
    margin: 10,
    position: "relative"
  },
  actions: {
    position: "absolute",
    bottom: 0
  },
  title: {
    fontSize: 14
  },
  pos: {
    marginBottom: 12
  },
  buttonProgress: {
    position: "relative",
    bottom: -15
  }
};

function renderAddNewCardActions(cardType, classes, callController) {
  if (cardType === "newCard") {
    return (
      <CardActions className={classes.actions} disableactionspacing="true">
        <IconButton onClick={callController}>
          <FloatingActionButtons />
        </IconButton>
      </CardActions>
    );
  }
}

class CardTemplate extends Component {
  constructor(props) {
    super(props);
    this.state = {
      part: "",
      result: this.props.result,
      value: "",
      day: this.props.day,
      loading: false,
      cardType: this.props.cardType
    };
    this.callController = this.callController.bind(this);
    this.renderAddNewCardContent = this.renderAddNewCardContent.bind(this);
    this.setCardTemplateState = this.setCardTemplateState.bind(this);
  }

  render() {
    const { classes } = this.props;

    return (
      <Card className={classes.card}>
        <CardContent>
          {this.renderPuzzleCardContent()}
          {this.renderAddNewCardContent()}
          {this.state.loading && (
            <CircularProgress className={classes.buttonProgress} />
          )}
        </CardContent>
        {renderAddNewCardActions(
          this.state.cardType,
          classes,
          this.callController
        )}
      </Card>
    );
  }

  renderPuzzleCardContent() {
    if (this.state.cardType === "puzzleCard") {
      return (
        <PuzzleCard
          callback={this.setCardTemplateState}
          result={this.state.result}
          day={this.state.day}
        />
      );
    }
  }
  renderAddNewCardContent() {
    if (this.state.cardType === "newCard") {
      return <AddNewCard callback={this.setCardTemplateState} />;
    }
  }

  setCardTemplateState(value) {
    this.setState(value);
  }

  async callController() {
    this.setState({ loading: true });
    const response = await fetch(
      "/api/adventOfCode/?day=" + this.state.day + "&part=" + this.state.value
    );
    try {
      const data = await response.json();
      const content = data.answer;
      const message = data.message;
      if (response.ok) {
        this.props.callback({
          day: this.state.day.toString(),
          part: this.state.value.toString(),
          result: content
        });
        this.setState({
          result: content,
          loading: false
        });
      } else {
        this.setState({ result: message, loading: false });
      }
    } catch (e) {
      this.setState({ result: "There has been a technical error." });
      this.setState({ loading: false });
    }
  }
}

CardTemplate.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(CardTemplate);

import React, { Component } from "react";
import PropTypes from "prop-types";
import { withStyles } from "@material-ui/core/styles";
import Typography from "@material-ui/core/Typography";

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
  }
};

class PuzzleCard extends Component {
  render() {
    const { classes } = this.props;

    return (
      <div>
        <Typography variant="h5" component="h2">
          Result for Day {this.props.day}
        </Typography>
        <Typography className={classes.pos} color="textSecondary" />
        <Typography component="p">
          {this.props.result}
        </Typography>
      </div>
    );
  }
}

PuzzleCard.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(PuzzleCard);

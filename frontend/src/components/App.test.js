import React from "react";
import ReactDOM from "react-dom";
import App from "./App";
import { shallow } from "enzyme";

describe("App", () => {
  it("should add puzzle cards provided by the state", () => {
    const component = shallow(<App />).dive();
    component.setState({
      solved: {
        1: { day: "1", part: "1", result: "abc" },
        2: { day: "1", part: "2", result: "def" },
        3: { day: "3", part: "1", result: "ghi" }
      }
    });
    const expectedResults = ["abc", "def", "ghi"];
    const actualPuzzleCards = component.instance().addPuzzleCards();

    expect(
        actualPuzzleCards.map(card => card.props.result)
    ).to.have.all.members(expectedResults);

    expect(
        actualPuzzleCards.map(card => card.props.cardType)
    ).to.have.all.members(["puzzleCard", "puzzleCard", "puzzleCard"]);

    expect(actualPuzzleCards.map(card => card.props.day)).to.have.all.members([
      "1",
      "1",
      "3"
    ]);

    expect(actualPuzzleCards.length).to.equal(3);

    let cardResults = [];
    actualPuzzleCards.forEach(card => cardResults.push(card.props.result));
    expect(cardResults[0]).to.equal("ghi");
    expect(cardResults[1]).to.equal("def");
    expect(cardResults[2]).to.equal("abc");
  });

  it("should render the desired childComponents", () => {
    const component = shallow(<App />).dive();

    console.log(component.childAt(0));

    expect(component.children().getElements().length).to.equal(2);

    expect(component.childAt(0).name()).to.equal(
        "WithStyles(PrimarySearchAppBar)"
    );
    expect(component.childAt(1).name()).to.equal("WithStyles(ForwardRef(Grid))");

    expect(
        component
            .childAt(1)
            .children()
            .getElements().length
    ).to.equal(1);

    expect(
        component
            .childAt(1)
            .childAt(0)
            .name()
    ).to.equal("WithStyles(CardTemplate)");
  });

  it("renders without crashing", () => {
    const div = document.createElement("div");
    ReactDOM.render(<App />, div);
    ReactDOM.unmountComponentAtNode(div);
  });
});
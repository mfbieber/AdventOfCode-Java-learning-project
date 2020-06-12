import React from "react";
import AddNewCard from "./AddNewCard";
import { shallow } from "enzyme";
import chai from "chai";
import spies from "chai-spies";

chai.use(spies);
const expect = chai.expect;

/*describe("AddNewCard", () => {
  it("should call handleSelect upon picking a day", () => {
    let mockCallback = chai.spy();
    const component = shallow(<AddNewCard callback={mockCallback} />).dive();
    component
      .find("WithStyles(WithFormControlContext(Select))")
      .simulate("change", { target: { value: 1 } });
    expect(mockCallback).to.have.been.called();
  });

  it("should add MenuItems that are provided through the state", () => {
    const component = shallow(<AddNewCard />).dive();
    component.setState({ daysImplemented: [1, 2, 3] });
    component.instance().renderMenuItems();
    let numberOfMenuItems = component.find("WithStyles(MenuItem)").getElements()
      .length;
    expect(numberOfMenuItems).to.equal(0);
    let menuItems = component.find("WithStyles(MenuItem)");
    let menuItemsText = [];
    menuItems.forEach(menuItem =>
      menuItemsText.push(menuItem.childAt(0).text())
    );
    expect(menuItemsText[0]).to.equal("None");
    expect(menuItemsText[1]).to.equal("December 1st");
    expect(menuItemsText[2]).to.equal("December 2nd");
    expect(menuItemsText[3]).to.equal("December 3rd");
  });
});*/

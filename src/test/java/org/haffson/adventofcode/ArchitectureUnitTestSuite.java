package org.haffson.adventofcode;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(ArchitectureUnitTest.class)
@Suite.SuiteClasses({ArchitectureUnitTesting.class})
public class ArchitectureUnitTestSuite {
}

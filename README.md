##Learn how to work with Java and SpringBoot with Advent of Code!

Clone or fork this repository and start implementing your solutions for https://adventofcode.com/ without having to worry about a project setup.

This readme will explain to you briefly how it works.

This project was intended for educational purposes only. It is intended not to be complete. Most things provided here are just starting
points to get you started with the mentioned technologies.

I believe that learning is done best, when you have a real example to work on. The tech stack used here
was similar to that of what we used in consulting projects in the year 2018. If you master all of these, you will be on a good way to become
a great developer.

The most important thing in my opinion while working with this if you are a complete beginner: get some support and somebody who reviews your code.
You might need somebody to guide you through the controllers and components in Spring too. Or you can check out the code and read the 
documentation yourself. Do whatever works for you.

####Overview of the project setup

This project contains a SpringBoot backend and a ReactJS frontend part. The frontend is just there to help you visualize the results, but you can also work
completely without it if you prefer. 

If working with the backend alone, we recommend to use Postman to trigger the REST endpoints of the backend to see the results.

Even better than that, would be if you worked with Test-Driven-Development techniques and implement tests before actually implementing the code that will 
solve the puzzle. If you do so, you won't even have to run the Spring Boot app to see a result. 

####Importing the project to IntelliJ

Install IntelliJ and then go to File/New/Project from Existing Sources... and import the project through the `build.gradle` file.

####Development build

If you imported the project as decribed above for IntelliJ, you should be able to run the Application class in `src/main/java/org/haffson/adventofcode`
by right-clicking on it and choosing "Run 'Application'".

The frontend can be started by first running `npm install` and then `npm run start` from the frontend directory.
It will start a development server that will serve the frontend at http://localhost:3000. Please refer also to the readme
for the frontend in the frontend folder.

####"Production" build
You can also run a complete build fot the frontend and backend together if you specify the environment variable "FE_BUILD=true" and then
run the gradle build. We used this to deploy this app to heroku. It serves builds the frontend and copies the optimized
production build to the resources folder of the backend. Then, the frontend will be served with the Spring Boot Application by Spring Boots's
Tomcat.

###Implementing solutions for Advent of Code (https://adventofcode.com/)

####Implementing the Interface `Days`

To make AdventOfCode pick up a new solution for a day of Advent of Code, you need to implement the `Days` interface. A rough
implementation is provided for `Day01`, although the puzzle solution is not provided there. 

The implementation of `Days` needs to be annotated with `@Component`. By this, `AdventOfCodeService` will be able to scan for all 
Beans that implement `Days` and pass that information on to the `AdventOfCodeController`. 

The `AdventOfCodeController` has a method `daysImplemented()` which tells the frontend which days have already been implemented. 

In addition, you should specify whether the problem part of an Advent of Code puzzle has already been solved (use the `ProblemStatusEnum`).

(see also the ADR at `src/docs/architecture`)

####Test-Driven-Development

We strongly recommend to first write tests and then the solution for the puzzles. Advent of Code is great for this,
because they provide lots of example input and output data for the puzzle. Thus, you can write a test with a simple input,
and write a test that expects that a method (that you need to implement) will produce a desired output.
 
Then you can start implementing that method and putting it into the e.g. `Day01` class and then calling it from `firstPart()`
as shown with `calculateFrequency()`. Of course it does not have an implementation yet - that is up to you!
 
Check `Day01Test` at `src/test/java/org/haffson/adventofcode/days/day01/`. We have also provided an example structure for the tests there.
 
###Work though the code and find more
If you want more, check out:
- The ArchUnit tests
- We started implementing a persistence layer together with liquibase and PostgreSQL. This is useful for not always having to calculate all the puzzles solutions as
it is done right now! Expecially if a puzzle needs large calculation time, we would run into problems with our current code! 
- We are using Spring REST docs to document the API of the controller. Check out the tests for it and the generated
docs at `/src/docs/asciidoc`.
- We also started documenting our architecture with *Architecture Decision Records*. You can see the docs at 
`src/docs/architecture`. You can continue to use those records whenever you add or change something in the projects architecture!
- Finally: feel free to check out the frontend code. We have a test there that is now working and unfortunately was just commented out. 





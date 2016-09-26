Narrative:
As a cook with a meal plan with multiple recipes
I want to see a list of all ingredients I'll need, with repeat ingredients shown only once
So that I can buy the right stuff from the supermarket

Scenario: The cook can view a list of ingredients needed for multiple recipes
Given that my meal planner contains pizzas, fajitas
When the shopping list helper is invoked
Then the shopping list contains pizza bases, tomato paste, mushrooms, peppers, onions, fajita mix, wraps, chicken, cheese once only
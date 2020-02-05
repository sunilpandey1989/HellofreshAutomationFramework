@UI
Feature: Signup Feature

  Background: User is able to open the URL
    Given User navigates to HomePage

  Scenario: New User Registration Successfully
    When User clicks on SignIn link
    And User enters valid EmailID in Email Address
    And User clicks on Create an account button
    And User fills all the details correctly
    And User clicks on Registration button
    Then User should see My Account Page
    Then Username should be visible in header
    Then User should be able to logout

  Scenario: User is able to login successfully
    When User clicks on SignIn link
    And User enters existing UserName and Password
    And User clicks on SignIn Button
    Then User should see My Account Page
    Then Username should be visible in header
    Then User should be able to logout

  Scenario: User is able to checkout and complete order successfully
    When User clicks on SignIn link
    And User enters existing UserName and Password
    And User clicks on SignIn Button
    And User clicks on Women Link
    And User adds a product and moves it in the cart
    Then User should see the added product in cart
    When User clicks on 'Proceed To Checkout'
    And User clicks on 'Proceed To Checkout' in Address Section
    And User clicks on 'Proceed To Checkout' in Shipping Section
    And User selects 'Bankwire' payment method
    And User confirms the order
    Then User should be able to verify order completion details

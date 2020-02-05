## Sign in
#### Preconditions
* Generated customer with all customer data
#### Test steps
* Open [Home page](http://automationpractice.com/index.php)
* Click *Sign in* button
* Fill *Email address* to create an account
* Click *Create an account* 
* Fill all fields with correct data 
* Click *Register* button
#### Assertions
* My account page(?controller=my-account) is opened
* Proper username is shown in the header
* Log out action is available

## Log in
#### Preconditions
* Existing customer
#### Test steps
* Open [Home page](http://automationpractice.com/index.php)
* Click *Sign in* button (in the header)
* Fill *Email address* in _Already registered_ block
* Fill *Password* in _Already registered_ block
* Click *Sign in* 
#### Assertions
* My account page(?controller=my-account) is opened
* Proper username is shown in the header
* Log out action is available

## Checkout
#### Preconditions
* Existing customer
* Order details
#### Test steps
* Log in as existing customer
* Click *Women* button in the header
* Click the product with name "Faded Short Sleeve T-shirts"
* Click *Add to card*
* Click *Proceed to checkout*
* Click *Proceed to checkout*
* Click *Proceed to checkout*
* Click by *Terms of service* to agree
* Click *Proceed to checkout*
* Click by payment method *Pay by bank wire*
* Click *I confirm my order*
#### Assertions
* Order confirmation page(?controller=order-confirmation) is opened
* The order is complete.
* Current page is the last step of ordering 
# ISU CAR RIDE
#Project Summary
The project we have chosen to complete is the logic for a car share system, similar to that of Uber or Lyft. This project will be able to cover three of the four required components, specifically reading/writing from text files, relational databases and SQL, and using multiple classes. This project will be written in Java, and using components learned in class to connect to databases and writing daily reports out to files. Challenges for this project will be setting up the proper environment to allow collaboration between team members without creating conflicts in code, and being able to cover edge cases while testing. 

## Project Design
### Classes
Likely classes needed to implement this solution:

**Driver–** This class will set up all the information we need from the driver such as what type of car they drive and their availability. It will also keep track of how much the driver makes and a review section to review the customer. 

**Customer-** This class will ask and set up all information for the customer such as their beginning and final destination, creating a login and finally having a driver review section to rate their experience with the driver.

**Database-** This class sets up all the table connections and statements to make sure that our databases all connect and have the correct information.

**Reports-** This class will create our daily reports such as total charges, number of rides taken that day and shows all customer and driver reviews.

### Class Methods
### Driver

**NewDriver**- Creates new driver entry and asks for their email, phone number, car make and model and license plate number. Will determine how many passengers they can take.

**GetAvailability-** A getter method to determine when a driver is available during the week.

**PayDriver-** Keeps track of how much money the driver has earned that day.

**BeginDriver-** Address they are picking up customer.

**EndDriver-** Ending address they are dropping customer off at.

**ReviewRider-** An option for the driver to rate their experience with the current customer.

### Customer

**NewCustomer-** Gets the basic information about the customer such as their name, payment method, email and phone number.

**CallRide-** Asks for the beginning and ending addresses, how many passengers will be with them, if they have any oversized luggage then asks what type of ride they are looking for (luxury, XL, or basic). 

**RiderCharges-** When the ride is finished, this method will calculate the amount due and ask the rider if they want to tip. It’ll determine based on location and time whether there are any surge fees or discounts for campus addresses.

**ReviewDriver-** An option for the customer to rate their experience with the current driver. 

### Reports

**DailyReports-** Will generate and write to a file the daily reports. The most important elements we have determined for the reports are the number of rides taken a day, the average distance the drivers drove, driver and customer ratings for the day, total charges and tips, and total payout to the drivers. This will then write all the information to a file. 
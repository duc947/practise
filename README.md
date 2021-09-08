# ATE – Pre/Post interview – v1.1
1. Test Application: https://www.pexels.com/
1. API documentation: https://www.pexels.com/api/documentation
1. Requirement: 
    - Selenium 3.xxx.xx 
    - Programing language: You can choose 1 of these following options: Java, Python, C#, Javascript 
    - Automation Framework: Using Selenium as the core. 
1. Submit the Test: 
    - Create a new private repository using github, gitlab, and share it to me or Zip the projects file and share it via email. 
    - Duration: 1 week 
## Test scenarios:
### Scenario 1: Follow a photographer successfully
* Given I log in with account "<account_name>"
* And I go to the home page.
* And I click the first photo on home page
* When I hover on “Follow” button at the top left corner
* And I click the Follow button
* Then I see the button text turn into Following
### Scenario 2: Update the avatar in the Profile page
* Given I log in with account "<account_name>"
* And I go to the Profile page
* When I click on “Complete Your profile” link
* And I upload a new avatar the username field
* And I update all information in the page
* And I click the Update profile button 
* Then I observe that it will take me to the Profile page
* And my new avatar is displayed.
### Scenario 3: List of liked photos
* Given I log in with account "<account_name>"
* And I like 3 random photos
* When I go to * https://www.pexels.com/<@profile>/likes/*
* Then I see the number of likes is 3
* And 3 photos appear in Likes section
### Scenario 4: Remove photos from the collection successfully
* Given I log in with account "<account_name>"
* And I create a private collection
* And I add 2 random photos to the newly created collection
* And I remove 1 photo from the newly created collection
* When I go to * https://www.pexels.com/collections/collection_ID/*
* Then I notice that the photo has been removed successfully from the collection
* And there is only 1 remaining photo in the collection
### Scenario 5: Download photo successfully
* Given I log in with account "<account_name>"
* When I open a random photo
* And I download this photo
* Then I notice that the image is downloadable and the correct image has been saved

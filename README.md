# vaccine-tracker
Spring Boot app to track vaccines for specific pin-codes, and send notifications to Telegram channels via bots

Create & setup your bot & channel in telegram & integrate in java code by following these steps -
1. Search for the account 'BotFather'
2. Start a chat, type in '/newbot' command
3. Fill the details as asked, like bot name & other stuff
4. BotFather will provide an API token for your newly created bot. 
5. Copy the API token and update the ToTelegram.java code
6. Create a new public channel in telegram and add your bot as an admin with only permission to send messages
7. Copy the channel id [t.me/channelId] into the ToTelegram.java code.
8. Run the springboot code


Initial Code feature-
1. Can add two pincodes in properties files to track the next day's open slots & send real time notifications to telegram channel via a telegram bot 


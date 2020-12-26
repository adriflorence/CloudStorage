# Cloud Storage
The minimum viable product includes three user-facing features:

1. **Simple File Storage:** Upload/download/remove files
2. **Note Management:** Add/update/remove text notes
3. **Password Management:** Save, edit, and delete website credentials.  

## Requirements and Road-map
Your tech lead is excited to work with you and has laid out a development roadmap with requirements and milestones. They tell you that there are three layers of the application you need to implement:

1. The back-end with Spring Boot
2. The front-end with Thymeleaf
3. Application tests with Selenium

### The Back-End

1. Managing user access with Spring Security
 - Restrict unauthorized users from accessing pages other than the login and signup pages.

2. Handling front-end calls with controllers

3. Making calls to the database with MyBatis mappers
 - Database schema was provided. The POJO fields match the names and data types in the schema, with one class per database table.
 - Model classes are connected to the database through MyBatis mapper interfaces for each of the model types. These mappers support the basic CRUD (Create, Read, Update, Delete) operations.


### The Front-End

1. Login page
 - Everyone can access it
 - Login errors (E.g invalid username/password) are displayed here

2. Sign Up page
 - Everyone can access it
 - Validates whether username supplied already exists

3. Home page
The home page is the center of the application and hosts the three required pieces of functionality. The existing template presents them as three tabs that can be clicked through by the user:

 i. Files
  - The user should be able to upload files and see any files they previously uploaded. 
  - The user should be able to view/download or delete previously-uploaded files.
  - Any errors related to file actions should be displayed. For example, a user should not be able to upload two files with the same name, but they'll never know unless you tell them!

 ii. Notes
  - The user should be able to create notes and see a list of the notes they have previously created.
  - The user should be able to edit or delete previously-created notes.

 iii. Credentials
 - The user should be able to store credentials for specific websites and see a list of the credentials they've previously stored. If you display passwords in this list, make sure they're encrypted!
 - The user should be able to view/edit or delete individual credentials. When the user views the credential, they should be able to see the unencrypted password.

The home page should have a logout button that allows the user to logout of the application and keep their data private.

### Testing

1. Tests for user signup, login, and unauthorized access restrictions:
 - a test that verifies that an unauthorized user can only access the login and signup pages.
 - a test that signs up a new user, logs in, verifies that the home page is accessible, logs out, and verifies that the home page is no longer accessible. 


2. Tests for note creation, viewing, editing, and deletion:
 - a test that creates a note, and verifies it is displayed.
 - a test that edits an existing note and verifies that the changes are displayed.
 - a test that deletes a note and verifies that the note is no longer displayed.


3. Tests for credential creation, viewing, editing, and deletion:
 - a test that creates a set of credentials, verifies that they are displayed, and verifies that the displayed password is encrypted.
 - a test that views an existing set of credentials, verifies that the viewable password is unencrypted, edits the credentials, and verifies that the changes are displayed.
 - a test that deletes an existing set of credentials and verifies that the credentials are no longer displayed.
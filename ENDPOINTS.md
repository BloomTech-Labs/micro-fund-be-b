###User specific endpoints

GET request to grab all users (https://microfund-b.herokuapp.com/users/all)

GET request to grab an individual user by their id (https://microfund-b.herokuapp.com/users/user/{id})

GET request to grab an individual user by their username (https://microfund-b.herokuapp.com/users/user/name/{username})

GET request to grab all users that username contains the given substring (https://microfund-b.herokuapp.com/users/user/name/like/{username})

GET request to grab the authenticated user's user information (https://microfund-b.herokuapp.com/users/getuserinfo)

GET request to grab an individual user's list of applications by their user id (https://microfund-b.herokuapp.com/users/user/{id}/apps)

POST request to create a new user with the given complete user object (https://microfund-b.herokuapp.com/users/user)

PUT request to overwrite an existing user according to id with the given complete user object (https://microfund-b.herokuapp.com/users/user/{id})

PATCH request to update an existing user fields according to id with the given user data (https://microfund-b.herokuapp.com/users/user/{id})

DELETE request to delete an existing user according to their id (https://microfund-b.herokuapp.com/users/user/{id})

###Organization specific endpoints

GET request to grab all organizations (https://microfund-b.herokuapp.com/orgs/all)

GET request to grab an individual organization by its id (https://microfund-b.herokuapp.com/orgs/org/{id})

GET request to grab an organization's associated users based on its id (https://microfund-b.herokuapp.com/orgs/org/{id}/users)

GET request to grab an organization's associated applications based on its id (https://microfund-b.herokuapp.com/orgs/org/{id}/apps)

POST request to create a new organization with the given complete organization object (https://microfund-b.herokuapp.com/orgs/org)

PATCH request to update an existing organization fields according to id with the given organization data (https://microfund-b.herokuapp.com/orgs/org/{id})

DELETE request to delete an existing organization according to their id (https://microfund-b.herokuapp.com/orgs/org/{id})
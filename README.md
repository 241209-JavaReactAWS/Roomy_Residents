# Roomy_Residents
## Endpoints
- **POST** userauth/login
- - Creates cookie upon creation and returns 200
- - Returns 404 on No user Found
- - Returns 401 on Invalid Password
- - Returns 500 otherwise
- **POST** userauth/register
- - Creates User and adds to database, 201 on success
- - Returns 400 on improper inputs
- - Returns 409 on Username conflict
- - Returns 412 on missing fields
- - Returns 500 otherwise
- **POST** userauth
- - Removes Current Login Key and Returns OK
- **GET** userauth
- - Obtain User Login Cookie and Returns OK
- - If there is no cookie, return 404
- -
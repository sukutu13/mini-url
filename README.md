# mini-url

Project created in order to shorten URLs.
Created in Java Spring Boot.

## Requests
### POST Request

Request URL: http://localhost:5000/minify

Request Body
```json
{
  "bigUrl":"example.com"
}
```

Request Response
```json
{
  "code": "00",
  "description": "Success",
  "id": "8dc6460",
  "timesShortened": 5,
  "timesClicked": 1
}
```

### GET Request

Request URL: http://localhost:5000/{id}

Request Response
```json
{
  "code": "00",
  "description": "Success",
  "bigUrl": "example.com"
}
```

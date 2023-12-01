import requests
import json

url = "http://localhost:8080/api/v1/bank/account"

requestData = {
    "password": "1234"
}

userContext = {
    "userId": 2,
    "accountId": 2,
    "accountType": "INDIVIDUAL"
}

# Combine requestData and userContext
data = {"password": requestData["password"]}

# Add userContext to headers
headers = {"userContext": json.dumps(userContext), "Content-Type": "application/json"}

# Perform the request with headers
response = requests.post(url, json=data, headers=headers)

print(response.status_code)
print(response.text)


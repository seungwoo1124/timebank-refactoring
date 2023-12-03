import requests
import json

url = "http://localhost:8080/api/v1/bank/account"

requestData = {
    "password": "seungwoo2"
}

#아래 두 변수는 헤더로 들어갑니다.
userContext = {
    "userId": 4,
    "accountId": 4,
    "accountType": "INDIVIDUAL"
}
appName = "timePay"

data = {"password": requestData["password"]}
headers = {"userContext": json.dumps(userContext), "appName": appName, "Content-Type": "application/json"}

response = requests.post(url, json=data, headers=headers)

print(response.status_code)
print(response.text)
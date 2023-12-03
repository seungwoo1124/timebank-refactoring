import requests
import json

# 테스트할 데이터
request_data = {
    'senderBankAccountNumber': '02-01-03-46',
    'receiverBankAccountNumber': '02-01-04-59',
    'amount': 120,
    'password': 'seungwoo'
}

# 송금 유저 컨텍스트
userContext = {
    "userId": 3,
    "accountId": 3,
    "accountType": "INDIVIDUAL"
}

# POST 요청 보내기
url = 'http://localhost:8080/api/v1/bank/account/transfer' 
headers = {"userContext": json.dumps(userContext), "Content-Type": "application/json"}
response = requests.post(url, headers=headers, data=json.dumps(request_data))

# 응답 확인
print(response.status_code)
print(response.text)


import pandas as pd
import win32com.client

def get_topten_jusik():
    
    objCpSvr8563 = win32com.client.Dispatch("Dscbo1.CpSvrNew7043")

    objCpSvr8563.SetInputValue(0, ord('0'))
    objCpSvr8563.SetInputValue(1, ord('2'))
    objCpSvr8563.SetInputValue(2, ord('1'))
    objCpSvr8563.SetInputValue(3, 21)
    objCpSvr8563.SetInputValue(4, ord('1'))
    objCpSvr8563.SetInputValue(5, ord('0'))
    objCpSvr8563.SetInputValue(6, ord('0'))
    objCpSvr8563.SetInputValue(7, 0)
    objCpSvr8563.SetInputValue(8, 30)


    objCpSvr8563.BlockRequest()

    data = pd.DataFrame(columns=[
        "stockCode",
        "stockName", 
        "price",
        "diffPer"
    ])

    cnt = objCpSvr8563.GetHeaderValue(0)

    for k in range(cnt):
        if k == 10:
            break

        data.append({
            "stockCode" : objCpSvr8563.GetDataValue(0, k),
            "stockName" : objCpSvr8563.GetDataValue(1, k),
            "price"     : objCpSvr8563.GetDataValue(2, k),
            "diffPer"      : objCpSvr8563.GetDataValue(5, k)
         }, ignore_index=True)

    return data

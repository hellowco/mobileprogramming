
import pandas as pd
import win32com.client

def get_topten_jusik():
    
    objCpSvr7043 = win32com.client.Dispatch("cpsysdib.CpSvrNew7043")

    objCpSvr7043.SetInputValue(0, ord('0'))
    objCpSvr7043.SetInputValue(1, ord('2'))
    objCpSvr7043.SetInputValue(2, ord('1'))
    objCpSvr7043.SetInputValue(3, 21)
    objCpSvr7043.SetInputValue(4, ord('1'))
    objCpSvr7043.SetInputValue(5, ord('0'))
    objCpSvr7043.SetInputValue(6, ord('0'))
    objCpSvr7043.SetInputValue(7, 0)
    objCpSvr7043.SetInputValue(8, 30)

    objCpSvr7043.BlockRequest()

    data = pd.DataFrame(columns=[
        "stockCode",
        "stockName", 
        "price",
        "diffPer"
    ])

    cnt = objCpSvr7043.GetHeaderValue(0)

    for k in range(cnt):
        if k == 10:
            break

        data = data.append({
            "stockCode" : objCpSvr7043.GetDataValue(0, k),
            "stockName" : objCpSvr7043.GetDataValue(1, k),
            "price"     : objCpSvr7043.GetDataValue(2, k),
            "diffPer"   : objCpSvr7043.GetDataValue(5, k)
         }, ignore_index=True)

    return data

if __name__ == "__main__":

    test = get_topten_jusik()
    print(test)
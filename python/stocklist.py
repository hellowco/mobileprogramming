import pandas as pd
import win32com.client

def getCodeName():

    # objMarketEye = win32com.client.Dispatch("CpSysDib.MarketEye")
    objCpCodeMgr = win32com.client.Dispatch("CpUtil.CpCodeMgr")

    # 주식 전종목
    codeList = objCpCodeMgr.GetStockListByMarket(1)  # 거래소
    codeList2 = objCpCodeMgr.GetStockListByMarket(2)  # 코스닥
    allcodelist = codeList + codeList2
    
    # print("주식 종목 코드 #", len(allcodelist))
    
    data = pd.DataFrame(columns=['Code', 'Name'])
    for code in allcodelist:
        name = objCpCodeMgr.CodeToName(code)
        data = data.append({'Code': code,'Name': name}, ignore_index=True)
        
    return data
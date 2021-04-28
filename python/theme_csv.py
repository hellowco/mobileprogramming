#########################
# 상승률 상위 테마 가져오는 코드
import pandas as pd
import win32com.client

def theme(datestring):
    print("상승률 상위 테마 가져오기")

    recommended = 5
    objCpSvr8563 = win32com.client.Dispatch("Dscbo1.CpSvr8563")

    #전일대비상승률 상위순
    value = None
    objCpSvr8563.SetInputValue(0, ord('1'))

    #전일대비상승률 하위순
    #objCpSvr8563.SetInputValue(0, ord('2'))
    #5일대비상승율 상위순
    #objCpSvr8563.SetInputValue(0, ord('3'))
    #5일대비상승률 하위순
    #objCpSvr8563.SetInputValue(0, ord('4'))
    #상승종목비율상위
    #objCpSvr8563.SetInputValue(0, ord('5'))
    #상승종목비율하위
    #objCpSvr8563.SetInputValue(0, ord('6'))

    objCpSvr8563.BlockRequest()

    data = pd.DataFrame(columns=["themeCode",
                                 "themeName", "compositItems",
                                 "dayCompare", "fivedaysCompare",
                                 "numOfRising", "numOfFalling",
                                 "percentOfRising"])
    
    # data index : 
    # 0 - (short)테마코드
    # 1 - (string)테마명
    # 2 - (short)구성종목수
    # 3 - (float)1일전대비
    # 4 - (float)5일전대비
    # 5 - (long)상승종목수
    # 6 - (long)하락종목수
    # 7 - (long)상승종목비율
        
    for k in range(recommended):
        themeCode = objCpSvr8563.GetDataValue(0, k)
        themeName = objCpSvr8563.GetDataValue(1, k)
        compositItems = objCpSvr8563.GetDataValue(2, k)
        dayCompare = objCpSvr8563.GetDataValue(3, k)
        fivedaysCompare = objCpSvr8563.GetDataValue(4, k)
        numOfRising  = objCpSvr8563.GetDataValue(5, k)
        numOfFalling = objCpSvr8563.GetDataValue(6, k)
        percentOfRising = objCpSvr8563.GetDataValue(7, k)

        data = data.append({"themeCode":themeCode,
                            "themeName":themeName,
                            "compositItems":compositItems,
                            "dayCompare":dayCompare,
                            "fivedaysCompare":fivedaysCompare,
                            "numOfRising":numOfRising,
                            "numOfFalling":numOfFalling,
                            "percentOfRising":percentOfRising}, ignore_index=True)

    # print(data)
    return data

def getStockFromTheme(themeCode):
    """
    테마코드를 받아서 해당 테마에 속한 종목 반환 recommended 변수로 종목 반환 수 조절.
    """
    print("테마당 종목 검색, 테마코드 : {0}".format(themeCode))
    recommended = 3
    objCpSvr8561T = win32com.client.Dispatch("Dscbo1.CpSvr8561T")
    data = pd.DataFrame(columns=["stockCode",
                                 "stockName", "curPrice",
                                 "diff", "percentOfDiff",
                                 "volume", "compareToYesterday",
                                 ])
    # data index:
    # 0 - (string)종목코드
    # 1 - (string)종목명
    # 2 - (long)현재가
    # 3 - (long)대비
    # 4 - (float)대비율
    # 5 - (long)거래량
    # 6 - (float) 전일동시간대비

    objCpSvr8561T.SetInputValue(0, themeCode)
    objCpSvr8561T.BlockRequest()

    for k in range(recommended):
        stockCode = objCpSvr8561T.GetDataValue (0, k)
        stockName = objCpSvr8561T.GetDataValue (1, k)
        curPrice = objCpSvr8561T.GetDataValue (2, k)
        diff = objCpSvr8561T.GetDataValue (3, k)
        percentOfDiff = objCpSvr8561T.GetDataValue (4, k)
        volume = objCpSvr8561T.GetDataValue (5, k)
        compareToYesterday = objCpSvr8561T.GetDataValue (6, k)

        data = data.append({"stockCode":stockCode,
                            "stockName":stockName,
                            "curPrice":curPrice,
                            "diff":diff,
                            "percentOfDiff":percentOfDiff,
                            "volume":volume,
                            "compareToYesterday":compareToYesterday}, ignore_index=True)

    # print(data)
    return data
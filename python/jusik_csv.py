#########################
# kospi,kosdaq 가져오는 코드

import pandas as pd
import win32com.client


def jusik():
    """
    모든 종목에 대한 PER, 순매수 받아오는 함수
    """
    print("PER 가져오기")

    # Create Object
    objCpCodeMgr = win32com.client.Dispatch("CpUtil.CpCodeMgr")
    objMarketEye = win32com.client.Dispatch("CpSysDib.MarketEye")

    # kospi = objCpCodeMgr.GetStockListByMarket(1)  # 코스피
    # kosdaq = objCpCodeMgr.GetStockListByMarket(2)  # 코스닥
    # list = [kospi, kosdaq]
    indCode = 182 # 180 kospi200, 182 kospi50
    list = objCpCodeMgr.GetGroupCodeList(indCode)  # indCode 업종코드

    # data순서 "번호","업종코드","주식코드","종목명","PER","전일 외국인순매수","전일 기관순매수"
    data = pd.DataFrame(columns=["indCode",
                                 "Code", "Name",
                                 "juga", "PER",
                                 "EPS", "ROE",
                                 "BPS", "PBR"])

    for code in list:
        # GetData 67: PER, 121: 전일 외국인 순매수, 122: 전일 기관순매수
        # 4 현재가 67 PER 70 EPS 77 ROE 89 BPS
        objMarketEye.SetInputValue(0, (4, 67, 70, 77, 89))
        objMarketEye.SetInputValue(1, code)
        name = objCpCodeMgr.CodeToName(code)
        indCode = objCpCodeMgr.GetStockIndustryCode(code)

        # BlockRequest
        objMarketEye.BlockRequest()

        # juga = objMarketEye.GetDataValue(0, 0)
        juga = objMarketEye.GetDataValue(0, 0)
        per = objMarketEye.GetDataValue(1, 0)
        eps = objMarketEye.GetDataValue(2, 0)
        roe = objMarketEye.GetDataValue(3, 0)
        bps = objMarketEye.GetDataValue(4, 0)
        if not (bps == 0):
            pbr = juga / bps

        # PrintData
        data = data.append({'indCode': indCode,
                            'Code': code,
                            'Name': name,
                            'juga': juga,
                            'PER': per,
                            'EPS': eps,
                            'ROE': roe,
                            'BPS': bps,
                            'PBR': pbr}, ignore_index=True)

    # print(data)
    return data

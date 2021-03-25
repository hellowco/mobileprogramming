import pandas as pd
import win32com.client


def netPurchase(investFlag):
    """ 기관(2), 외국인(1)을 investFlag로 받아서 순매수를 반환 """
    # Create Object
    objCpSvr = win32com.client.Dispatch("CpSysDib.CpSvr7210d")

    objCpSvr.SetInputValue(0, '0')  # 0 전체 1 거래소 2 코스닥 3 업종 4 관심종목
    objCpSvr.SetInputValue(1, ord('1'))  # 0 수량 1 금액
    objCpSvr.SetInputValue(2, investFlag)  # 0 종목 1 외국 2 기관
    objCpSvr.SetInputValue(3, ord('0'))  # 0 상위 1 하위

    # BlockRequest
    objCpSvr.BlockRequest()

    data = pd.DataFrame(columns=["code", "종목명",
                                 "현재가", "대비",
                                 "대비율", "거래량",
                                 "외국인", "기관계"])
    cnt = objCpSvr.GetHeaderValue(0)
    date = objCpSvr.GetHeaderValue(1)  # 집계날짜
    time = objCpSvr.GetHeaderValue(2)  # 집계시간
    print(cnt, date, time)

    for i in range(0, 10):
        data = data.append({'code': objCpSvr.GetDataValue(0, i),
                            '종목명': objCpSvr.GetDataValue(1, i),
                            '현재가': objCpSvr.GetDataValue(2, i),
                            '대비': objCpSvr.GetDataValue(3, i),
                            '대비율': objCpSvr.GetDataValue(4, i),
                            '거래량': objCpSvr.GetDataValue(5, i),
                            '외국인': objCpSvr.GetDataValue(6, i),
                            '기관계': objCpSvr.GetDataValue(7, i)
                            }, ignore_index=True)
        # item = {}
        # item['보험기타금융'] = objCpSvr.GetDataValue(8, i)
        # item['투신'] = objCpSvr.GetDataValue(9, i)
        # item['은행'] = objCpSvr.GetDataValue(10, i)
        # item['연기금'] = objCpSvr.GetDataValue(11, i)
        # item['국가지자체'] = objCpSvr.GetDataValue(12, i)
        # item['기타법인'] = objCpSvr.GetDataValue(13, i)

    return data

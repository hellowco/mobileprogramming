from sqlalchemy import create_engine
import pymysql
import time
import serverInfo
import jusik_csv
import netPurchase
from pywinauto import application
from datetime import datetime

if __name__ == "__main__":

    # try :
    #     # 대신 login
    #     app = application.Application()
    #     print("cybosplus 실행")
    #     app.start('C:\\DAISHIN\\STARTER\\ncStarter.exe /prj:cp /id:isungun1 /pwd:aptes#1 /pwdcert:doqxptmxm#1 /autostart')
    #     time.sleep(30)
    # except: 
    #     print('증권 api 로그인 실패')
    try: 
        #DB login
        db_connection_str = 'mysql+pymysql://root:root@localhost/test'
        db_connection = create_engine(db_connection_str)
        conn = db_connection.connect()
    except:
        print('db connection failed')

    dateString = datetime.strftime(datetime.now(), '%Y%m%d')

    # get api data
    per = jusik_csv.jusik(dateString)
    orgNetPurchase= netPurchase.netPurchase(1)
    forNetPurchase = netPurchase.netPurchase(2)

    # print("외국인 상위 10", netPurchase.netPurchase(1))
    # print("기관계 상위 10", netPurchase.netPurchase(2))

    # data to db
    # per.to_sql(name=dateString + '_PER', con=db_connection, if_exists='append',index=False) #if_exists : append, replace, fail(dafault)
    # orgNetPurchase.to_sql(name='orgData', con=db_connection, if_exists='replace',index=False)
    # forNetPurchase.to_sql(name='forData', con=db_connection, if_exists='replace',index=False)

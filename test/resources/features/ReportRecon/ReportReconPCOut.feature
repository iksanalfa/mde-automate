Feature: Report Rekon Payment Credit Outbound

Scenario Outline: Report Rekon Payment Credit Outbound
	Given Transaksi <transactionType> dengan Processing Code <processingCode> senilai <transactionAmount> Acquirer Paynet <acquirerId> Issuer <issuerId>
  And Response Code <responseCode> dan Tanggal Transaksi <yyyymmdd>
  When Running workflow report <reportName>
  Then Validate Report QRX_RECON_360004_<transactionType>_<yymmdd>_ACQ_1 compare with <processingCode> <transactionAmount> <yyyymmdd> <responseCode> <acquirerId> <issuerId>
  And  Validate Report QRX_RECON_360004_<transactionType>_<yymmdd>_ISS_1 compare with <processingCode> <transactionAmount> <yyyymmdd> <responseCode> <acquirerId> <issuerId>
  
Examples:
 |transactionType|processingCode|transactionAmount|acquirerId|issuerId|responseCode|yyyymmdd|reportName|yymmdd|
 |Outbound|260000|10.01|94580000|93600911|00|20210818|Recon|210818|

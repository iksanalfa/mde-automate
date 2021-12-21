Feature: Report Rekon Payment Credit Inbound

Scenario Outline: Report Rekon <messageType> <transactionType> <scenario>
	Given Transaksi <transactionType> dengan Processing Code <processingCode> senilai <transactionAmount> Acquirer Paynet <acquirerId> Issuer <issuerId> Rrn <rrn> TransId <transId> Response Code <responseCode> Tanggal Transaksi <transactionDate> Loop <loop>
  And Check Transaction Manager Date <transactionDate> Rrn <rrn>
  When Running workflow report
  And Moving File Report
  #Then Validate Report QRX_RECON_360004_<transactionType>_<yymmdd>_ACQ_1 compare with <processingCode> <transactionAmount> <yyyymmdd> <responseCode> <acquirerId> <issuerId>
  #And  Validate Report QRX_RECON_360004_<transactionType>_<yymmdd>_ISS_1 compare with <processingCode> <transactionAmount> <yyyymmdd> <responseCode> <acquirerId> <issuerId>
  
Examples:
|scenario|transactionType|messageType|processingCode|transactionAmount|acquirerId|issuerId|rrn|transId|responseCode|transactionDate|XtransactionAmount|XacquirerId|XissuerId|loop|
|1;1;1|Inbound;Inbound;Inbound|Inquiry;Payment Credit;Check Status|381000;261000;361000|2000;3000;3000|93600008;93600008;93600008|94580991;94580991;94580991|123980180420;123980180421;123980180421|8031;8032;8033|00;68;A0|20211216;20211216;20211216|2000;3000;3000|93600008;93600008;93600008|94580991;94580991;94580991|y;y;y|
|2|Inbound|Payment Credit|260000|80000|93600008|94580991|123980180422|8034|00|20211216|80000|93600008|94580991|n|
|3|Inbound|Payment Credit|260000|90000|93600008|94580991|123980180423|8035|00|20211216|90000|93600008|94580991|n|
|4|Inbound|Payment Credit|260000|90000|93600008|94580991|123980180423|8035|00|20211216|90000|93600008|94580991|n|

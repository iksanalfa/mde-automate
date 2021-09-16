Feature: Report Rekon Payment Credit Inbound

  Scenario Outline: Report Rekon Payment Credit Inbound
    Given Transaksi Menggunakan Parameter Pada Excel Sheet <sheetName> Row <rowNumber>
    And Cek Transaksi Pada Transaction Manager
    When Jalankan Worklow Report
    And Memindahkan File Report
    Then Validasi Report Rekon Acquirer
    And Validasi Report Rekon Issuer
Examples:
 |sheetName|rowNumber|
 |Sheet1|0|
 |Sheet1|1|
 |Sheet1|2|

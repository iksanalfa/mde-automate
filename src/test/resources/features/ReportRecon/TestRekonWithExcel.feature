Feature: Test Excel

  Scenario Outline: Test Excel
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
|Sheet1|3|
|Sheet1|4|
|Sheet1|5|

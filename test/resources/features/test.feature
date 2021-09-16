Feature: Validate QRX_RECON

Scenario Outline: Get Every Column Value of Report
	Given Report Result Exist <fileName>
	When Read Report File
	Then Get Every Column Value <fileName>
	
Examples: 
| fileName |
| QRX_RECON_360004_000008_210728_ACQ_1 |
| QRX_RECON_360004_000911_210728_ISS_1 |
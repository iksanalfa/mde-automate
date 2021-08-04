Feature: Validate File

Scenario Outline: Read Result File
Given Write a textfile <filename> with line1 is <line1> and line2 is <line2>
When Result File <filename> Created
Then Read and Validate Result File <filename>

Examples:
	| filename | line1 | line2 |
	| QRXCSB_SEND_360004 | ITMX | OUTBOUND |

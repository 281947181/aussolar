<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<sst xmlns="http://schemas.openxmlformats.org/spreadsheetml/2006/main"
	count="37" uniqueCount="37">
	<si>
		<t>Job number</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>Pick up by</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>Planned Install Date &amp; Time</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>Pick up at</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>Customer Name</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>Customer contact</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>Address</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>Installation Details</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>Roof</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>Phase</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>Panels</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>Panels no.</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>Inverter</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>Planned pick up date</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>Tilt Frame</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>Install info &amp; comments</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>Payment method</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>Payment due</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>Reference Photos</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>Panel location highlight</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>Suburb</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>Postcode</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>${projectInfo.project_id}</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>${projectInfo.customer_name}</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>${projectInfo.customer_phone}</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>${projectInfo.customer_address}</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>${projectInfo.customer_subburb}</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>${projectInfo.customer_postcode}</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>${projectInfo.customer_roof_type}</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>${projectInfo.customer_phase_type}</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>${quoteRecord.panel_brand}</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>${quoteRecord.panel_number}</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>${quoteRecord.inverter_brand}</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t>${quoteRecord.payment}</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t><#if
			quoteRecord.deposit??>${quoteRecord.price-quoteRecord.deposit}<#else>${quoteRecord.price}</#if>
		</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t><#if
			quoteRecord.install_comment??>${quoteRecord.install_comment}<#else></#if>
		</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
	<si>
		<t><#if
			projectInfo.install_comment??>${projectInfo.install_comment}<#else></#if>
		</t>
		<phoneticPr fontId="1" type="noConversion" />
	</si>
</sst>
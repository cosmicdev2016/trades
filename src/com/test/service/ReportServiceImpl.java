package com.test.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import com.test.model.InstructionInfo;
import com.test.util.ReportServiceHelper;


public class ReportServiceImpl implements IReportService {

	@Override
	public void generateTextReport(List<InstructionInfo> tradeData) {

		Map<LocalDate, Double> outgoing = ReportServiceHelper.getAmountFlowingEveryday(tradeData, 'B');
		Map<LocalDate, Double> incoming = ReportServiceHelper.getAmountFlowingEveryday(tradeData, 'S');

		System.out.println("Amount in USD settled outgoing everyday");
		outgoing.entrySet().forEach(entry -> System.out.println("Date: " + entry.getKey() + ", Amount: " + entry.getValue()));

		System.out.println("\n------------------------------------------------------\n");

		System.out.println("Amount in USD settled incoming everyday");
		incoming.entrySet().forEach(entry -> System.out.println("Date: " + entry.getKey() + ", Amount: " + entry.getValue()));

		SortedMap<Integer, Set<String>> rankOutgoing = ReportServiceHelper.getRankOfEntities(tradeData, 'B');
		SortedMap<Integer, Set<String>> rankIncoming = ReportServiceHelper.getRankOfEntities(tradeData, 'S');

		System.out.println("\n------------------------------------------------------\n");

		System.out.println("Rank outgoing entities");
		rankOutgoing.entrySet().stream()
				.forEach(entry -> System.out.println("Rank: " + entry.getKey() + ", Entity(s): " + entry.getValue()));

		System.out.println("\n------------------------------------------------------\n");

		System.out.println("Rank incoming entities");
		rankIncoming.entrySet().stream()
				.forEach(entry -> System.out.println("Rank: " + entry.getKey() + ", Entity(s): " + entry.getValue()));
	}
}

package com.moses.sorting.controller;

import com.moses.sorting.model.SortingResult;
import com.moses.sorting.service.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class SortingController {

    private final SortingService sortingService;

    @Autowired
    public SortingController(SortingService sortingService) {
        this.sortingService = sortingService;
    }

    @GetMapping
    public String showSortingForm() {
        return "sort-form";
    }

    @PostMapping
    public String sortArray(
            @RequestParam("algorithm") String algorithm,
            @RequestParam("array") String arrayInput,
            Model model
    ) {
        try {
            SortingResult result = sortingService.sortArray(algorithm, arrayInput);

            model.addAttribute("sortedArray", result.getSortedArray());
            model.addAttribute("swaps", result.getSwaps());
            model.addAttribute("passes", result.getPasses());
            model.addAttribute("timeComplexity", result.getTimeComplexity());

            return "sort-form";
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());

            // Add error to the model
            model.addAttribute("error", "Error: " + e.getMessage());
            return "sort-form";
        }
    }
}

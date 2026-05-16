package com.hacakthon.team1.project.domain.service;

import com.hacakthon.team1.application.exception.BusinessException;
import com.hacakthon.team1.application.exception.ErrorCode;
import com.hacakthon.team1.project.domain.entity.Project;
import com.hacakthon.team1.project.domain.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectQueryService {

    private final ProjectRepository projectRepository;

    public Project findById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public List<Project> search(String keyword, List<String> techStacks, boolean recruitingOnly, String sort) {
        java.util.Comparator<Project> comparator = "popular".equalsIgnoreCase(sort)
                ? java.util.Comparator.comparingInt(Project::getLikeCount).reversed()
                : java.util.Comparator.comparing(Project::getCreatedAt).reversed();

        return projectRepository.findAll().stream()
                .filter(p -> keyword == null || keyword.isBlank() ||
                        normalize(p.getTitle()).contains(normalize(keyword)) ||
                        p.getTechStacks().stream().anyMatch(t -> normalize(t).contains(normalize(keyword))))
                .filter(p -> techStacks == null || techStacks.isEmpty() ||
                        techStacks.stream().anyMatch(t -> p.getTechStacks().contains(t)))
                .filter(p -> !recruitingOnly || p.getStatus() == com.hacakthon.team1.project.domain.entity.ProjectStatus.RECRUITING)
                .sorted(comparator)
                .toList();
    }

    public List<String> findPopularTechStacks(int limit) {
        return projectRepository.findAll().stream()
                .flatMap(p -> p.getTechStacks().stream())
                .collect(java.util.stream.Collectors.groupingBy(t -> t, java.util.stream.Collectors.counting()))
                .entrySet().stream()
                .sorted(java.util.Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(limit)
                .map(java.util.Map.Entry::getKey)
                .toList();
    }

    private String normalize(String value) {
        return value.replace(" ", "").toLowerCase();
    }
}

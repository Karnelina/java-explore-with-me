package ru.practicum.controller.category;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.aspect.ToLog;
import ru.practicum.dto.category.CategoryDto;
import ru.practicum.mapper.CategoryMapper;
import ru.practicum.model.Category;
import ru.practicum.service.CategoryService;
import ru.practicum.util.OffsetBasedPageRequest;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Collection;
import java.util.stream.Collectors;

import static ru.practicum.Constant.PAGE_DEFAULT_FROM;
import static ru.practicum.Constant.PAGE_DEFAULT_SIZE;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Validated
@ToLog
public class PublicCategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public Collection<CategoryDto> getAllCategories(@RequestParam(defaultValue = PAGE_DEFAULT_FROM)
                                                    @PositiveOrZero Integer from,
                                                    @RequestParam(defaultValue = PAGE_DEFAULT_SIZE)
                                                    @Positive Integer size) {
        Pageable page = new OffsetBasedPageRequest(from, size);
        return categoryService.getAllCategories(page)
                .stream()
                .map(categoryMapper::toCategoryDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{catId}")
    public CategoryDto getCategoryById(@Positive @PathVariable Long catId) {
        Category category = categoryService.getCategoryById(catId);
        return categoryMapper.toCategoryDto(category);
    }
}

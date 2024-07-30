package org.example.timesheet.service.impl;

import jakarta.transaction.Transactional;
import org.example.timesheet.base.BaseResponse;
import org.example.timesheet.dto.pagination.PageDto;
import org.example.timesheet.dto.position.PositionDTO;
import org.example.timesheet.dto.position.PositionRequest;
import org.example.timesheet.dto.position.PositionResponse;
import org.example.timesheet.entity.Position;
import org.example.timesheet.exception.NotFoundException;
import org.example.timesheet.mapper.PositionMapper;
import org.example.timesheet.repository.PositionRepository;
import org.example.timesheet.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionRepository repository;


    @Override
    public BaseResponse createPosition(PositionRequest request) {
        Position positionCheck = repository.findByPosition(request.getPosition());
        if (positionCheck != null) {
            throw new NotFoundException(false, 404, "position exists");
        }
        Position position = new Position().setPosition(request.getPosition())
                .setCreated(new Date());
        repository.save(position);
        return new BaseResponse(true, 200, "create successfully");
    }

    @Override
    public PositionResponse getPositions(Pageable pageable) {
        Page<Position> positionPage = repository.findAll(pageable);
        List<PositionDTO> dtos = positionPage.getContent()
                .stream()
                .map(PositionMapper.MAPPER::toDTO)
                .collect(Collectors.toList());
        return new PositionResponse(true, 200)
                .setPositionDTOList(dtos)
                .setPageDto(PageDto.populatePageDto(positionPage));
    }

    @Override
    @Transactional
    public PositionResponse updatePosition(PositionRequest request) {
        Optional<Position> position = repository.findById(request.getId());
        if (position.isEmpty()) {
            throw new NotFoundException(false, 404, "position not found");
        }
        PositionMapper.MAPPER.updatePositionFromRequest(request, position.get());
        return new PositionResponse(true, 200)
                .setPositionDTO(PositionMapper.MAPPER.toDTO(position.get()));
    }

    @Override
    public PositionResponse deletePosition(Long id) {
        Optional<Position> position = repository.findById(id);
        if (position.isEmpty()) {
            throw new NotFoundException(false, 404, "position not found");
        }
        repository.delete(position.get());
        return new PositionResponse(true, 200, "delete position successfully");
    }
}

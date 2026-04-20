// Author: Member 1 & 2
package com.ooad.leave.service;

import com.ooad.leave.model.Employee;
import com.ooad.leave.model.LeaveRequest;
import com.ooad.leave.repository.LeaveRequestRepository;
import com.ooad.leave.patterns.observer.LeaveStatusChangedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LeaveService {

    @Autowired
    private LeaveRequestRepository leaveRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public LeaveRequest applyLeave(LeaveRequest request) {
        request.setStatus("PENDING");
        request.setAppliedOn(LocalDate.now());
        LeaveRequest saved = leaveRepository.save(request);
        // Observer Pattern Trigger
        eventPublisher.publishEvent(new LeaveStatusChangedEvent(this, saved));
        return saved;
    }
    
    public LeaveRequest cancelLeave(Long leaveId) {
        LeaveRequest leave = leaveRepository.findById(leaveId).orElseThrow();
        leave.cancel(); // Behavioral Pattern: State
        LeaveRequest saved = leaveRepository.save(leave);
        eventPublisher.publishEvent(new LeaveStatusChangedEvent(this, saved));
        return saved;
    }

    public List<LeaveRequest> getEmployeeLeaves(Employee emp) {
        return leaveRepository.findByEmployee(emp);
    }
    
    public List<LeaveRequest> getAllPendingLeaves() {
        return leaveRepository.findByStatus("PENDING");
    }

    public LeaveRequest approveLeave(Long leaveId) {
        LeaveRequest leave = leaveRepository.findById(leaveId).orElseThrow();
        leave.approve(); // State Transition
        LeaveRequest saved = leaveRepository.save(leave);
        eventPublisher.publishEvent(new LeaveStatusChangedEvent(this, saved));
        return saved;
    }

    public LeaveRequest rejectLeave(Long leaveId) {
        LeaveRequest leave = leaveRepository.findById(leaveId).orElseThrow();
        leave.reject(); // State Transition
        LeaveRequest saved = leaveRepository.save(leave);
        eventPublisher.publishEvent(new LeaveStatusChangedEvent(this, saved));
        return saved;
    }
}

package by.bsu.fantasy.controller;

import by.bsu.fantasy.dto.PickDTO;
import by.bsu.fantasy.model.Pick;
import by.bsu.fantasy.model.User;
import by.bsu.fantasy.util.AuthPolicy;
import by.bsu.fantasy.util.DtoMappingUtil;
import by.bsu.fantasy.util.JwtTokenRepository;
import by.bsu.fantasy.util.SetAuthPolicy;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import by.bsu.fantasy.service.PickPlayerService;
import by.bsu.fantasy.service.PickService;
import by.bsu.fantasy.service.PickTeamService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class PickController {
    private final PickService pickService;
    private final PickPlayerService pickPlayerService;
    private final PickTeamService pickTeamService;
    private final JwtTokenRepository jwtTokenRepository;

    private boolean checkPickAccess(HttpServletRequest authRequest, Long id) {
        User user = jwtTokenRepository.getUserFromRequest(authRequest);
        if (user == null) {
            return false;
        }
        return user.getPicks().stream().anyMatch(el -> el.getId() == id);
    }

    @SetAuthPolicy(policy = AuthPolicy.ADMIN)
    @GetMapping("/picks")
    public List<PickDTO> getPicks() {
        return pickService.getPicks()
                .stream()
                .map(DtoMappingUtil::convert)
                .collect(Collectors.toList());
    }

    @SetAuthPolicy(policy = AuthPolicy.USER)
    @GetMapping("/pick/{id}")
    public ResponseEntity<PickDTO> getPickById(HttpServletRequest request, @PathVariable Long id) {
        if (!checkPickAccess(request, id)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(DtoMappingUtil.convert(
                pickService.getPickById(id)
        ), HttpStatus.OK);
    }

    @SetAuthPolicy(policy = AuthPolicy.ADMIN)
    @PostMapping("/pick")
    public PickDTO createPick(@RequestBody Pick pick) {
        pick.setBalance(1000.0);
        return DtoMappingUtil.convert(
                pickService.createPick(pick)
        );
    }

    @SetAuthPolicy(policy = AuthPolicy.ADMIN)
    @PutMapping("/pick/{id}")
    public PickDTO updatePick(@PathVariable Long id, @RequestBody Pick pick) {
        return DtoMappingUtil.convert(
                pickService.updatePick(id, pick)
        );
    }

    @SetAuthPolicy(policy = AuthPolicy.ADMIN)
    @DeleteMapping("/pick/{id}")
    public void deletePick(@PathVariable Long id) {
        pickService.deletePick(id);
    }

    @SetAuthPolicy(policy = AuthPolicy.USER)
    @PutMapping("/pick/{id}/addPlayer/{playerId}")
    public ResponseEntity<PickDTO> addPlayer(HttpServletRequest request, @PathVariable Long id, @PathVariable Long playerId) {
        if (!checkPickAccess(request, id)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(DtoMappingUtil.convert(
                pickPlayerService.linkPlayerToPick(id, playerId)
        ), HttpStatus.OK);
    }

    @SetAuthPolicy(policy = AuthPolicy.USER)
    @PutMapping("/pick/{id}/addTeam/{teamId}")
    public ResponseEntity<PickDTO> addTeam(HttpServletRequest request, @PathVariable Long id, @PathVariable Long teamId) {
        if (!checkPickAccess(request, id)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(DtoMappingUtil.convert(
            pickTeamService.linkTeamToPick(id, teamId)
        ), HttpStatus.OK);
    }

    @SetAuthPolicy(policy = AuthPolicy.USER)
    @DeleteMapping("/pick/{id}/removePlayer/{playerId}")
    public ResponseEntity<PickDTO> removePlayer(HttpServletRequest request, @PathVariable Long id, @PathVariable Long playerId) {
        if (!checkPickAccess(request, id)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(DtoMappingUtil.convert(
            pickPlayerService.unlinkPlayerFromPick(id, playerId)
        ), HttpStatus.OK);
    }

    @SetAuthPolicy(policy = AuthPolicy.USER)
    @DeleteMapping("/pick/{id}/removeTeam/{teamId}")
    public ResponseEntity<PickDTO> removeTeam(HttpServletRequest request, @PathVariable Long id, @PathVariable Long teamId) {
        if (!checkPickAccess(request, id)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(DtoMappingUtil.convert(
            pickTeamService.unlinkTeamFromPick(id, teamId)
        ), HttpStatus.OK);
    }

}
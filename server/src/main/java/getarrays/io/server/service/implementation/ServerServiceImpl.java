package getarrays.io.server.service.implementation;

import getarrays.io.server.enumeration.Status;
import getarrays.io.server.model.Server;
import getarrays.io.server.repository.ServerRepository;
import getarrays.io.server.service.ServerService;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static getarrays.io.server.enumeration.Status.SERVER_DOWN;
import static getarrays.io.server.enumeration.Status.SERVER_UP;
import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;

    @Override
    public Server create(Server server) {
        log.info("Saving new server: {}", server.getName());
        server.setImageUrl(setImagerUrl());
        return serverRepository.save(server);
    }

    @Override
    public Server ping(String ipAddress) {
        log.info("Pinging server IP: {}", ipAddress);
        Server server = serverRepository.findByIpAddress(ipAddress).orElse(
                new Server(null, ipAddress, "Unknown", "Unknown", "Unknown", setImagerUrl(), Status.SERVER_DOWN)
        );
        try {
            InetAddress address = InetAddress.getByName(ipAddress);
            server.setStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        serverRepository.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers");
        return serverRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by ID: {}", id);
        return serverRepository.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating server: {}", server.getName());
        return serverRepository.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server by ID: {}", id);
        serverRepository.deleteById(id);
        return TRUE;
    }

    private String setImagerUrl() {
        String[] imageNames = { "s1.png", "s2.png", "s3.png", "s4.png" };

        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/" + imageNames[new Random().nextInt(4)]).toUriString();
    }
}

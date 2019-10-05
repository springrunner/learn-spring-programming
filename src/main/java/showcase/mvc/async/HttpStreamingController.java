package showcase.mvc.async;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/async/streaming/")
public class HttpStreamingController {

    private final static int DEFAULT_MAX_SEND_COUNT = 5;

    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();


    @RequestMapping(value = "/response-body-emitter")
    public ResponseEntity<ResponseBodyEmitter> responseBodyEmitter() {
        final ResponseBodyEmitter emitter = new ResponseBodyEmitter(600000L);

        executor.schedule(new Runnable() {
            final AtomicInteger counter = new AtomicInteger(0);

            @Override
            public void run() {
                try {
                    if (counter.incrementAndGet() <= DEFAULT_MAX_SEND_COUNT) {
                        emitter.send(String.format("Count publish: %d\n", counter.get()), MediaType.TEXT_PLAIN);
                        executor.schedule(this, 2, TimeUnit.SECONDS);
                    } else {
                        emitter.complete();
                    }
                } catch (IOException error) {
                    throw new RuntimeException(error);
                }
            }
        }, 1, TimeUnit.SECONDS);

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(emitter);
    }

    @RequestMapping(value = "/sse-emitter")
    public SseEmitter sseEmitter() {
        final SseEmitter emitter = new SseEmitter(600000L);

        executor.schedule(new Runnable() {
            final AtomicInteger counter = new AtomicInteger(0);

            @Override
            public void run() {
                try {
                    if (counter.incrementAndGet() <= DEFAULT_MAX_SEND_COUNT) {
                        emitter.send(SseEmitter.event().name("counting").data(String.format("Count publish: %d\n", counter.get()), MediaType.TEXT_PLAIN));
                        executor.schedule(this, 2, TimeUnit.SECONDS);
                    } else {
                        emitter.send(SseEmitter.event().name("complete").data("", MediaType.TEXT_PLAIN));
                        emitter.complete();
                    }
                } catch (IOException error) {
                    throw new RuntimeException(error);
                }
            }
        }, 1, TimeUnit.SECONDS);

        return emitter;
    }

    @RequestMapping(value = "/streaming-response-body")
    public ResponseEntity<StreamingResponseBody> streamingResponseBody() {
        final StreamingResponseBody responseBody = new StreamingResponseBody() {
            final AtomicInteger counter = new AtomicInteger(0);

            @Override
            public void writeTo(OutputStream outputStream) throws IOException {
                while (counter.incrementAndGet() <= DEFAULT_MAX_SEND_COUNT) {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        counter.set(DEFAULT_MAX_SEND_COUNT + 1);
                    }

                    outputStream.write(String.format("Count publish: %d\n", counter.get()).getBytes());
                    outputStream.flush();
                }

                outputStream.close();
            }
        };

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(responseBody);
    }

}

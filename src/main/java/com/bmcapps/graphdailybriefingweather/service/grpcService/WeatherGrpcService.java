package com.bmcapps.graphdailybriefingweather.service.grpcService;

import com.bmcapps.graphdailybriefingweather.WeatherRequest;
import com.bmcapps.graphdailybriefingweather.WeatherResponse;
import com.bmcapps.graphdailybriefingweather.WeatherServiceGrpc;
import com.bmcapps.graphdailybriefingweather.model.graphSchema.WeatherSchema;
import com.bmcapps.graphdailybriefingweather.service.WeatherService;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class WeatherGrpcService extends WeatherServiceGrpc.WeatherServiceImplBase {

    private final WeatherService weatherService; // Your existing WeatherService

    @Autowired
    public WeatherGrpcService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    public void getWeather(WeatherRequest request, StreamObserver<WeatherResponse> responseObserver) {
        String city = request.getCity();
        String state = request.getState();

        WeatherSchema weather = weatherService.getWeatherForLocation(city, state);

        WeatherResponse response = WeatherResponse.newBuilder()
                .setTemperature(weather.getTemperature())
                .setPrecipitation(weather.getPrecipitation())
                .setRelativeHumidity(weather.getRelativeHumidity())
                .setWindSpeed(weather.getWindSpeed())
                .setWindDirection(weather.getWindDirection())
                .setWindGusts(weather.getWindGusts())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

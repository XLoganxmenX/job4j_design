package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Disabled("Тесты отключены")
class ParkingImplTest {

    @Test
    void whenParkSmallVehicleThenAdd() {
        Vehicle bmw = new PassengerVehicle("BMW V6");
        Parking parking = new ParkingImpl(8, 8);

        parking.park(bmw);

        assertThat(parking.findAll()).hasSize(1)
                .containsExactly(bmw);
    }

    @Test
    void whenParkVehicleThenAssignId() {
        AbstractVehicle bmw = new PassengerVehicle("BMW V6");
        Parking parking = new ParkingImpl(8, 8);

        parking.park(bmw);

        assertThat(bmw.getId()).isEqualTo(1);
    }

    @Test
    void whenParkBigVehicleThenAdd() {
        Vehicle kamaz = new Truck("Kamaz", 4);
        Parking parking = new ParkingImpl(8, 8);

        parking.park(kamaz);

        assertThat(parking.findAll()).hasSize(1)
                .containsExactly(kamaz);
    }

    @Test
    void whenParkBigVehicleAndBigPlacesHasNoSpaceThenAddToSmallPlace() {
        Vehicle bmw = new PassengerVehicle("BMW V6");
        Vehicle kamaz = new Truck("Kamaz", 4);
        ParkingImpl parking = new ParkingImpl(8, 3);

        parking.park(bmw);
        parking.park(kamaz);

        assertThat(parking.findAll())
                .hasSize(2)
                .contains(bmw, kamaz);
        assertThat(parking.getRemainBigPlaces()).isEqualTo(0);
        assertThat(parking.getRemainSmallPlaces()).isEqualTo(6);
    }

    @Test
    void whenParkSmallVehiclesAndFindAll() {
        Vehicle bmw = new PassengerVehicle("BMW V6");
        Vehicle lada = new PassengerVehicle("Lada Kalina");
        Vehicle niva = new PassengerVehicle("Niva");
        Parking parking = new ParkingImpl(8, 8);

        parking.park(bmw);
        parking.park(lada);
        parking.park(niva);

        assertThat(parking.findAll()).hasSize(3)
                .contains(bmw, lada, niva);
    }

    @Test
    void whenParkBigVehiclesAndFindAll() {
        Vehicle kamaz = new Truck("Kamaz", 4);
        Vehicle gazel = new Truck("Gazel", 2);
        Vehicle volvo = new Truck("Volvo", 8);
        Parking parking = new ParkingImpl(8, 16);

        parking.park(kamaz);
        parking.park(gazel);
        parking.park(volvo);

        assertThat(parking.findAll()).hasSize(3)
                .contains(kamaz, gazel, volvo);
    }

    @Test
    void whenParkBigAndSmallVehiclesAndFindAll() {
        Vehicle kamaz = new Truck("Kamaz", 4);
        Vehicle gazel = new Truck("Gazel", 2);
        Vehicle volvo = new Truck("Volvo", 8);
        Vehicle bmw = new PassengerVehicle("BMW V6");
        Vehicle lada = new PassengerVehicle("Lada Kalina");
        Vehicle niva = new PassengerVehicle("Niva");
        Parking parking = new ParkingImpl(8, 16);

        parking.park(kamaz);
        parking.park(gazel);
        parking.park(volvo);
        parking.park(bmw);
        parking.park(lada);
        parking.park(niva);

        assertThat(parking.findAll()).hasSize(6)
                .contains(kamaz, gazel, volvo, bmw, lada, niva);
    }

    @Test
    void whenParkSmallVehiclesThenDecreaseRemainSmallPlaces() {
        Vehicle bmw = new PassengerVehicle("BMW V6");
        Vehicle lada = new PassengerVehicle("Lada Kalina");
        Vehicle niva = new PassengerVehicle("Niva");
        ParkingImpl parking = new ParkingImpl(8, 8);

        parking.park(bmw);
        parking.park(lada);
        parking.park(niva);

        assertThat(parking.getRemainSmallPlaces()).isEqualTo(5);
        assertThat(parking.getRemainBigPlaces()).isEqualTo(8);
    }


    @Test
    void whenParkBigVehiclesThenDecreaseRemainBigPlaces() {
        Vehicle kamaz = new Truck("Kamaz", 4);
        Vehicle gazel = new Truck("Gazel", 2);
        Vehicle volvo = new Truck("Volvo", 8);
        ParkingImpl parking = new ParkingImpl(8, 16);

        parking.park(kamaz);
        parking.park(gazel);
        parking.park(volvo);

        assertThat(parking.getRemainBigPlaces()).isEqualTo(4);
        assertThat(parking.getRemainSmallPlaces()).isEqualTo(8);
    }

    @Test
    void whenParkBigAndSmallVehiclesThenDecreaseRemainPlaces() {
        Vehicle kamaz = new Truck("Kamaz", 4);
        Vehicle bmw = new PassengerVehicle("BMW V6");
        ParkingImpl parking = new ParkingImpl(8, 16);

        parking.park(kamaz);
        parking.park(bmw);

        assertThat(parking.getRemainBigPlaces()).isEqualTo(12);
        assertThat(parking.getRemainSmallPlaces()).isEqualTo(7);
    }

    @Test
    void whenParkSmallVehiclesAndParkingFullThenThrowException() {
        Vehicle bmw = new PassengerVehicle("BMW V6");
        Vehicle lada = new PassengerVehicle("Lada Kalina");
        Parking parking = new ParkingImpl(1, 8);

        parking.park(bmw);

        assertThatThrownBy(() -> parking.park(lada))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Недостаточно места");
    }

    @Test
    void whenReplaceSmallVehicles() {
        Vehicle bmw = new PassengerVehicle("BMW V6");
        Vehicle lada = new PassengerVehicle("Lada Kalina");
        Parking parking = new ParkingImpl(8, 16);

        parking.park(bmw);

        assertThat(parking.replace(1, lada)).isEqualTo(bmw);
        assertThat(parking.findAll()).hasSize(1)
                .containsExactly(lada);
    }

    @Test
    void whenReplaceSmallAndBigVehiclesThenThrowException() {
        Vehicle bmw = new PassengerVehicle("BMW V6");
        Vehicle kamaz = new Truck("Kamaz", 4);
        Parking parking = new ParkingImpl(8, 16);

        parking.park(bmw);

        assertThatThrownBy(() -> parking.replace(1, kamaz))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Невозможно заменить автомобили с разным размером");
    }

    @Test
    void whenRemoveVehicles() {
        Vehicle bmw = new PassengerVehicle("BMW V6");
        Vehicle kamaz = new Truck("Kamaz", 4);
        Parking parking = new ParkingImpl(8, 16);

        parking.park(bmw);
        parking.park(kamaz);

        assertThat(parking.remove(bmw)).isEqualTo(bmw);
        assertThat(parking.remove(kamaz)).isEqualTo(kamaz);
        assertThat(parking.findAll()).isEmpty();
    }

    @Test
    void whenFindById() {
        Vehicle bmw = new PassengerVehicle("BMW V6");
        Vehicle kamaz = new Truck("Kamaz", 4);
        Parking parking = new ParkingImpl(8, 16);

        parking.park(bmw);
        parking.park(kamaz);

        assertThat(parking.findById(1)).isEqualTo(bmw);
        assertThat(parking.findById(2)).isEqualTo(kamaz);
    }
}
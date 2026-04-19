package insurance.homeService.Dto;

import java.util.UUID;

public record DaskRequest(

        UUID userId,
        UUID customerId,
        String addressCode,
        Double squareMeter,//metre kare
        int floorNumber,//bulunduğğu kat
        String BuildStyle,  //bina yapı tarzı
        int numberBuildFloor,//kat sayısı
        String damageState,// hasar durumu
        int buildingAge
) {
}

public class Main {

    public static void main(String[] args) {
        SocialNetwork sn = new SocialNetwork();
        sn.load("SocialNetwork.txt");
        System.out.println(sn.count());
        System.out.println(sn.distance("MILFORD_STAUP", "MILLARD_VELTRI"));
    }


}

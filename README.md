NOT REALLY USEFULL FOR ANYTHING... have fun


he trobat aixo i igual ens serveix mes tard: tput cup 0 0 //mou el cursor a la fila 0 i columna 0

POSAR LES CONSTANTS NEGATIVES aixi segur que no coincideixen amb ascii
OÍDO BARRA!

explicació fork/exec no se quan s'ha de fer servir però te pinta que és clau
http://cortesfernando.blogspot.com.es/2011/11/procesos-linux-exec-y-fork.html

La funcio {chars_llegits =  super.read(caracters, offset, length)}. No es nescessari posar offset i length -> super.read(caracters)

TTY /dev/tty 
is a special file, representing the terminal for the current process. So, when you echo 1 > /dev/tty, your message ('1') will appear on your screen. Likewise, when you cat /dev/tty, your subsequent input gets duplicated (until you press Ctrl-C).

Com tancar el while?? Sembla mes elegant pero com no se com tancar el while no sirve de nada.
@Override
    public int read() throws IOException{
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("");
        String s = "";
        while(sc.hasNext()){
            s = sc.next();
            System.out.println(s);
        }
        return 0;
    }

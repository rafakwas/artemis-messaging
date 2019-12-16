# Konfiguracja

1. Pobrać i rozpakowac Artemis http://activemq.apache.org/components/classic/download/. Stworzyć instancję master.

````
sudo cp -r apache-artemis-2.9.0 /opt/

echo 'export PATH=/opt/apache-artemis-2.9.0/bin:$PATH' >> $HOME/.bashrc

. ~/.bashrc

artemis create $HOME/eawbase-broker-master --user=developer --password=developer --allow-anonymous
````

3. Analogicznie do punktu 2. stworzyć instancję master.
4. Podmienić plik broker.xml (~/eawbase/broker-master/etc/broker.xml) plikiem broker/server0/broker.xml 
5. Analigocznie do punktu 4-go podmienienić broker.xml instancji slave plikiem broker/server1/broker.xml. Nalezy jeszcze zmodyfikowac plik etc/bootstrap.xml instancji slave (zmiana portu panelu webowego z 8161 na 8162.
6. Uruchomić message brokery

````
"$HOME/eawbase-broker-master/bin/artemis-service" start
"$HOME/eawbase-broker-slave/bin/artemis-service" start
````

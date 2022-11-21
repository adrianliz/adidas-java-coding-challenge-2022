#/bin/zsh

USERNAME=$1
PASSWORD=$2

docker exec keycloak /opt/jboss/keycloak/bin/add-user-keycloak.sh --user $USERNAME --password $PASSWORD
docker restart keycloak

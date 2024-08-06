DESCRIPTION = "An UPnP front-end to MPD, the Music Player Daemon."
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRCREV = "upmpdcli-v1.8.12"
SRC_URI = "https://framagit.org/medoc92/upmpdcli;branch=master;protocol=https"

inherit autotools pkgconfig

S = "${WORKDIR}/git"

DEPENDS += " libupnp"

do_configure:prepend(){
    (cd ${S} && ${S}/autogen.sh)
}


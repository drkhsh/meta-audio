DESCRIPTION = "An UPnP front-end to MPD, the Music Player Daemon."
LICENSE = "GPL-2.0-only"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRCREV = "upmpdcli-v1.8.12"
SRC_URI = "git://framagit.org/medoc92/upmpdcli;branch=master;protocol=https \
           file://upmpdcli.conf"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"
SRC_URI[sha256sum] = "a24388c3f238c2b21bfd265a1ed84c8001110efa69c309b22d7465402d8cca03"

inherit meson pkgconfig systemd useradd

S = "${WORKDIR}/git"

DEPENDS += " libupnpp jsoncpp libmpdclient"
RDEPENDS:${PN} += " libupnpp jsoncpp libmpdclient python3-core"

SYSTEMD_SERVICE:${PN} = "upmpdcli.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

inherit useradd
USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "--system --no-create-home --groups audio \
                       --shell /bin/false --user-group upmpdcli"

do_install:append() {
    install -d 0755 ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/upmpdcli.conf ${D}${sysconfdir}/upmpdcli.conf

    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${S}/systemd/upmpdcli.service ${D}/${systemd_unitdir}/system

    install -d ${D}/var/cache/upmpdcli
    chown -R upmpdcli:upmpdcli ${D}/var/cache/upmpdcli
}

FILES:${PN} += " ${systemd_unitdir}/system/upmpdcli.service \
                 /var/cache/upmpdcli"


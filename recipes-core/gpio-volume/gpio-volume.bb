LICENSE="MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += " file://gpio-volume.py \
             file://gpio-volume.service"

RDEPENDS:${PN} = "python3-core python3-pyalsaaudio rpi-gpio"

S="${WORKDIR}"

do_install () {
	install -d ${D}${bindir}
    install -d ${D}/${systemd_user_unitdir}/

	install -m 0755 ${S}/gpio-volume.py ${D}${bindir}
	install -m 0644 ${S}/gpio-volume.service ${D}/${systemd_user_unitdir}/
}

FILES:${PN} += " ${bindir}/gpio-volume.py \
                 ${systemd_user_unitdir}/gpio-volume.service"

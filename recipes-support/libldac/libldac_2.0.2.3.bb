SUMMARY = "AOSP libldac"
DESCRIPTION = "AOSP libldac"
HOMEPAGE = "https://github.com/EHfive/ldacBT"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRCREV = "af2dd23979453bcd1cad7c4086af5fb421a955c5"
PV = "+git${SRCPV}"
SRC_URI = "git://github.com/EHfive/ldacBT.git;branch=master;protocol=https"

DEPENDS += " git"

S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE = "-S ${S} -B ${B} -DCMAKE_CROSSCOMPILING=TRUE -DCMAKE_INSTALL_PREFIX=/usr -DLDAC_SOFT_FLOAT=OFF"

do_configure:prepend() {
  cd ${WORKDIR}/git
  git submodule update --init --recursive

  git submodule init
  git submodule set-url libldac https://gitlab.com/eh5/libldac.git
  git -c protocol.file.allow=always submodule update
}

INSANE_SKIP:${PN} = "already-stripped"

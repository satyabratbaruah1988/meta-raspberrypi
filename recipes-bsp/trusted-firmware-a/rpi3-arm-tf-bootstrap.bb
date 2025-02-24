DESCRIPTION = "Bootstrap for rpi-3 arm trusted firmware"
LICENSE = "MIT"
PACKAGE_ARCH = "${MACHINE_ARCH}"

BB_STRICT_CHECKSUM = "0"

inherit deploy

SRC_URI = "git://github.com/AntonioND/rpi3-arm-tf-bootstrap.git;protocol=https;branch=master"
SRCREV = "879f2064e02980d36cacd8075937846228c4626f"
LIC_FILES_CHKSUM = "file://${S}/license.rst;md5=39c08dd7a1cf071b046d09e5cfc2e45e"
SRC_URI[sha256sum] = "eee88c12e1f8281a5c85ac0bcf8aabdab356cd91562348a10828fdd93efcf4be"


COMPATIBLE_MACHINE = "^rpi$"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

export CROSS_COMPILE="${TARGET_PREFIX}"

do_compile(){
    cd ${S}/aarch64/
    ./build.sh
}

do_install(){
    install -d ${D}${nonarch_base_libdir}/bootfiles
    install -m 0444 ${S}/aarch64/el2-bootstrap.bin ${D}${nonarch_base_libdir}/bootfiles
}

#do_deploy(){
#    install -d ${D}${nonarch_base_libdir}/bootfiles
#    cp ${S}/aarch64/el2-bootstrap.bin ${D}${nonarch_base_libdir}/bootfiles
#}

FILES:${PN} = "${nonarch_base_libdir}/bootfiles/el2-bootstrap.bin"
	.global main
	.align 4
main:
!locals=1, max_args=1
	save %sp,-96,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (NAME wSZ)))]
	mov 4,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t1 assigned to reg %l1
	mov %o0,%l1
! [MOVE (VAR 1) (TEMP 1)]
	mov %l1,%l0
	st %l0,[%fp-4]
! [MOVE (TEMP 2) (CALL (NAME A_go) ( (VAR 1)))]
	ld [%fp-4],%l0
	st %l0,[%sp+68]
	call A_go
	nop
	mov %o0,%l2
!>> Temp t2 assigned to reg %l3
	mov %o0,%l3
! [CALLST (NAME print) ( (TEMP 2))]
	ld [%l3],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
	ret
	restore

	.align 4
A_go:
!locals=0, max_args=0
	save %sp,-96,%sp
! [CJUMP >= (CONST 1) (CONST 2) (NAME L0)]
	mov 1,%l0
	mov 2,%l1
	cmp %l0,%l1
	bge L0
	nop
! [CALLST (NAME print) ( (CONST 1))]
	mov 1,%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
! [JUMP (NAME L1)]
	ba L1
	nop
! [LABEL L0]
L0:
! [CJUMP != (BINOP * (CONST 3) (CONST 4)) (CONST 10) (NAME L2)]
	mov 3,%l0
	mov 4,%l1
	smul %l0,%l1,%l0
	mov %l0,%l0
	mov 10,%l1
	cmp %l0,%l1
	bne L2
	nop
! [CALLST (NAME print) ( (CONST 4))]
	mov 4,%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
! [JUMP (NAME L3)]
	ba L3
	nop
! [LABEL L2]
L2:
! [CALLST (NAME print) ( (CONST 5))]
	mov 5,%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
! [LABEL L3]
L3:
! [LABEL L1]
L1:
! [RETURN (CONST 6)]
	ret
	restore
	ret
	restore

L$1:	.asciz "%d\n"

!Total regs:  5
!Total insts: 64
